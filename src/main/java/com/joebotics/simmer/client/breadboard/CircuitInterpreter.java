package com.joebotics.simmer.client.breadboard;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.joebotics.simmer.client.elcomp.AbstractCircuitElement;
import com.joebotics.simmer.client.elcomp.CircuitNode;
import com.joebotics.simmer.client.elcomp.CircuitNodeLink;
import com.joebotics.simmer.client.gui.util.Point;
import org.eclipse.jetty.util.ajax.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CircuitInterpreter {

	private Map<String, List<ConnectionPoint>> linkedConnectionPoints = new HashMap<>();
	private Map<String, ConnectionPoint> connectionPoints = new HashMap<>();
	private Map<AbstractCircuitElement, List<Connection>> connectionsFor = new HashMap<>();
	private Map<String, List<Connection>> connectionsForByName = new HashMap<>();
	private List<CircuitNode> circuitNodeList;
	private List<AbstractCircuitElement> circuitElements = new ArrayList<>();
	private List<Connection> connections= new ArrayList<>();

	public CircuitInterpreter(List<CircuitNode> circuitNodeList){
		this.circuitNodeList = circuitNodeList;
		analyze();
	}

	private void analyze(){

		for( CircuitNode n : circuitNodeList ){

			for( CircuitNodeLink cnl : n.links ){
				Point p = cnl.getElm().getPost(cnl.getNum());
				ConnectionPoint cp = new ConnectionPoint(p, cnl.getNum(), cnl.getElm());
				connectionPoints.put(cp+"", cp);

				if( !circuitElements.contains(cnl.getElm()) ){
					circuitElements.add(cnl.getElm());
				}

				if( !linkedConnectionPoints.containsKey(p.coords()) ){
					linkedConnectionPoints.put(p.coords(), new ArrayList<ConnectionPoint>());
				}

				if( !linkedConnectionPoints.get(p.coords()).contains(cp) ){
					linkedConnectionPoints.get(p.coords()).add(cp);
				}
			}
		}

		for( AbstractCircuitElement ce : circuitElements ){

			if( !connectionsFor.containsKey(ce) ){
				List l = new ArrayList<Connection>();
				connectionsFor.put(ce, l);
				connectionsForByName.put(ce.toString(), l);
			}

			for( int i=0; i<ce.getPostCount(); i++ ) {
				ConnectionPoint cp = connectionPoints.get(ce + ":" + i);
				List<ConnectionPoint> links = linkedConnectionPoints.get(cp.coords());

				for( ConnectionPoint cpt : links ){

					if( cpt.toString().equals(cp.toString()) )
						continue;

					Connection c = new Connection(cp, cpt);
					connections.add(c);
					connectionsFor.get(cp.getElement()).add(c);
				}
			}
		}

		log( toJSONObject().toString() );
	}

	public List<ConnectionPoint> getLinkedConnectionPoints(String coords) {
		return linkedConnectionPoints.get(coords);
	}

	public List<Connection> getConnectionsFor(AbstractCircuitElement el) {
		return connectionsFor.get(el);
	}

	public List<Connection> getConnectionsFor(String name) {
		return connectionsForByName.get(name);
	}

	public List<AbstractCircuitElement> getCircuitElements(){
		return circuitElements;
	}

	public List<Connection> getConnections() {
		return connections;
	}

	public List<String> getComponentNames(){
		List<String> result = new ArrayList<>();
		for( AbstractCircuitElement ace : circuitElements ){
			result.add(ace.toString());
		}

		return result;
	}

	private String componentNamesToJson(){
		String result = "[";

		for( String s : getComponentNames() ){
			result += "\"" + s + "\",";
		}

		return result.substring(0, result.length()-1) + "]";
	}

	public JSONObject toJSONObject(){
		JSONObject result = new JSONObject();

		JSONArray components = new JSONArray();
		result.put("components", components);
		JSONObject connections = new JSONObject();
		result.put("connections", connections);

		for( int i=0; i<getComponentNames().size(); i++ ){
			String name = getComponentNames().get(i);
			components.set(i, new JSONString(name));
			connections.put(name, createArray(this.connectionsForByName.get(name)));
		}

		return result;
	}

	private JSONArray createArray(List<Connection> connections){
		JSONArray result = new JSONArray();

		for( Connection c : connections ){
			result.set(result.size(), c.toJSONObject());
		}

		return result;
	}

	public native void log(String message) /*-{
        $wnd.console.log(message);
    }-*/;
}