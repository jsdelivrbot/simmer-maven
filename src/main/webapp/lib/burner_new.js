!function(t){var i={};function n(e){if(i[e])return i[e].exports;var r=i[e]={i:e,l:!1,exports:{}};return t[e].call(r.exports,r,r.exports,n),r.l=!0,r.exports}n.m=t,n.c=i,n.d=function(t,i,e){n.o(t,i)||Object.defineProperty(t,i,{configurable:!1,enumerable:!0,get:e})},n.r=function(t){Object.defineProperty(t,"__esModule",{value:!0})},n.n=function(t){var i=t&&t.__esModule?function(){return t.default}:function(){return t};return n.d(i,"a",i),i},n.o=function(t,i){return Object.prototype.hasOwnProperty.call(t,i)},n.p="",n(n.s=6)}([function(t,i,n){"use strict";var e=function(){function t(t,i){for(var n=0;n<i.length;n++){var e=i[n];e.enumerable=e.enumerable||!1,e.configurable=!0,"value"in e&&(e.writable=!0),Object.defineProperty(t,e.key,e)}}return function(i,n,e){return n&&t(i.prototype,n),e&&t(i,e),i}}();t.exports=function(){function t(){!function(t,i){if(!(t instanceof i))throw new TypeError("Cannot call a class as a function")}(this,t),this.children=[]}return e(t,[{key:"draw",value:function(){}},{key:"update",value:function(){}},{key:"setDirty",value:function(t){this.isDirty=t}}]),t}()},function(t,i,n){"use strict";var e=function(){function t(t,i){for(var n=0;n<i.length;n++){var e=i[n];e.enumerable=e.enumerable||!1,e.configurable=!0,"value"in e&&(e.writable=!0),Object.defineProperty(t,e.key,e)}}return function(i,n,e){return n&&t(i.prototype,n),e&&t(i,e),i}}();var r=function(){function t(){!function(t,i){if(!(t instanceof i))throw new TypeError("Cannot call a class as a function")}(this,t),this.running=!1}return e(t,[{key:"load",value:function(t){var i=arguments.length>1&&void 0!==arguments[1]?arguments[1]:[];this.tree=i,this.stop(),this.context=origami(t),this.drawBackground(),this.drawBorder()}},{key:"start",value:function(){this.running=!0,this.run()}},{key:"stop",value:function(){this.running=!1}},{key:"run",value:function(){var t=this;this.running&&(this.render(this.tree),window.requestAnimationFrame(function(){return t.run()}))}},{key:"render",value:function(){var t=this;(arguments.length>0&&void 0!==arguments[0]?arguments[0]:[]).forEach(function(i){i.isDirty&&(i.update(),i.draw(t.context),i.setDirty(!1),t.context.draw()),t.render(i.children)})}},{key:"drawBackground",value:function(){this.context.background("black").draw()}},{key:"drawBorder",value:function(){this.context.border({border:"1px solid #F00"}).draw()}}]),t}();t.exports=r},function(t,i,n){"use strict";var e=function(){function t(t,i){for(var n=0;n<i.length;n++){var e=i[n];e.enumerable=e.enumerable||!1,e.configurable=!0,"value"in e&&(e.writable=!0),Object.defineProperty(t,e.key,e)}}return function(i,n,e){return n&&t(i.prototype,n),e&&t(i,e),i}}();var r=n(0);t.exports=function(t){function i(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};!function(t,i){if(!(t instanceof i))throw new TypeError("Cannot call a class as a function")}(this,i);var n=function(t,i){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!i||"object"!=typeof i&&"function"!=typeof i?t:i}(this,(i.__proto__||Object.getPrototypeOf(i)).call(this));return n.config=t,n.connections=[],n}return function(t,i){if("function"!=typeof i&&null!==i)throw new TypeError("Super expression must either be null or a function, not "+typeof i);t.prototype=Object.create(i&&i.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),i&&(Object.setPrototypeOf?Object.setPrototypeOf(t,i):t.__proto__=i)}(i,r),e(i,[{key:"load",value:function(t){var i=this;this.highlight([]),this.connections=t.map(function(t){return{background:{type:"circle",x:t.x,y:t.y,width:i.config.width,color:i.config.color},title:t.title}})}},{key:"highlight",value:function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:[];this.highlighted=t,this.setDirty(!0)}},{key:"update",value:function(){var t=this;this.connections.forEach(function(i,n){return i.active=-1!==t.highlighted.indexOf(n)})}},{key:"draw",value:function(t){this.connections.forEach(function(i){switch(i.background.type){case"circle":i.active?t.arc(i.background.x,i.background.y,i.background.width,{background:i.background.color}).text(i.title,i.background.x,i.background.y,{color:"black",font:"12px Arial",align:"center"}):t.arc(i.background.x,i.background.y,i.background.width,{background:"black"})}})}}]),i}()},function(t,i,n){"use strict";var e=function(){function t(t,i){for(var n=0;n<i.length;n++){var e=i[n];e.enumerable=e.enumerable||!1,e.configurable=!0,"value"in e&&(e.writable=!0),Object.defineProperty(t,e.key,e)}}return function(i,n,e){return n&&t(i.prototype,n),e&&t(i,e),i}}();var r=n(0);t.exports=function(t){function i(t){!function(t,i){if(!(t instanceof i))throw new TypeError("Cannot call a class as a function")}(this,i);var n=function(t,i){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!i||"object"!=typeof i&&"function"!=typeof i?t:i}(this,(i.__proto__||Object.getPrototypeOf(i)).call(this));return n.config=t,n.activeRows={},n.rows=[],n}return function(t,i){if("function"!=typeof i&&null!==i)throw new TypeError("Super expression must either be null or a function, not "+typeof i);t.prototype=Object.create(i&&i.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),i&&(Object.setPrototypeOf?Object.setPrototypeOf(t,i):t.__proto__=i)}(i,r),e(i,[{key:"draw",value:function(t){this.rows.forEach(function(i){t.rect(i.x,i.y,i.width,i.height,{background:i.active?"white":"black"})})}},{key:"update",value:function(){var t=this.config.thickness||3,i=this.config.pitch||15,n=0,e=0;this.config.vertical?(n=this.config.width||t,e=this.config.height||i*this.config.rowCount):(n=this.config.width||5*i,e=this.config.height||t);var r=this.config.leftMargin+this.config.offsetX,o=this.config.topMargin+this.config.offsetY;this.rows=[];for(var a=0;a<this.config.rows;a++){var s=this.config.vertical?r+a*i:r,u=this.config.vertical?o:o+a*i,l=n,c=e;s-=t/2,u-=t/2,this.rows.push({x:Math.floor(s),y:Math.floor(u),width:l,height:c,active:this.activeRows[a]})}}},{key:"highlight",value:function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:[];this.activeRows=t.reduce(function(t,i){return t[i]=!0,t},{}),this.setDirty(!0)}}]),i}()},function(t,i,n){"use strict";var e=function(){function t(t,i){for(var n=0;n<i.length;n++){var e=i[n];e.enumerable=e.enumerable||!1,e.configurable=!0,"value"in e&&(e.writable=!0),Object.defineProperty(t,e.key,e)}}return function(i,n,e){return n&&t(i.prototype,n),e&&t(i,e),i}}(),r=function t(i,n,e){null===i&&(i=Function.prototype);var r=Object.getOwnPropertyDescriptor(i,n);if(void 0===r){var o=Object.getPrototypeOf(i);return null===o?void 0:t(o,n,e)}if("value"in r)return r.value;var a=r.get;return void 0!==a?a.call(e):void 0};var o=n(0),a=n(3);t.exports=function(t){function i(t){!function(t,i){if(!(t instanceof i))throw new TypeError("Cannot call a class as a function")}(this,i);var n=function(t,i){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!i||"object"!=typeof i&&"function"!=typeof i?t:i}(this,(i.__proto__||Object.getPrototypeOf(i)).call(this));return n.init(t),n}return function(t,i){if("function"!=typeof i&&null!==i)throw new TypeError("Super expression must either be null or a function, not "+typeof i);t.prototype=Object.create(i&&i.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),i&&(Object.setPrototypeOf?Object.setPrototypeOf(t,i):t.__proto__=i)}(i,o),e(i,[{key:"init",value:function(t){this.initBanks(t.banks)}},{key:"initBanks",value:function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:[];this.banks=t,this.children=this.banks.map(function(t){return new a(t)})}},{key:"draw",value:function(t){r(i.prototype.__proto__||Object.getPrototypeOf(i.prototype),"draw",this).call(this,t)}},{key:"update",value:function(){r(i.prototype.__proto__||Object.getPrototypeOf(i.prototype),"update",this).call(this)}},{key:"highlight",value:function(t){var i=this;this.hideBanks(),t.forEach(function(t){return i.children[t.index].highlight(t.rows)})}},{key:"hideBanks",value:function(){this.children.forEach(function(t){return t.highlight()})}}]),i}()},function(t,i,n){"use strict";var e=function(){function t(t,i){for(var n=0;n<i.length;n++){var e=i[n];e.enumerable=e.enumerable||!1,e.configurable=!0,"value"in e&&(e.writable=!0),Object.defineProperty(t,e.key,e)}}return function(i,n,e){return n&&t(i.prototype,n),e&&t(i,e),i}}();var r=n(4),o=n(2),a=n(1),s=function(){function t(i){!function(t,i){if(!(t instanceof i))throw new TypeError("Cannot call a class as a function")}(this,t),this.config=i,this.init()}return e(t,[{key:"init",value:function(){this.activeStepIndex=-1,this.steps=[],this.layers=[],this.connections={},this.initBreadboard(),this.initConnections(),this.initDrawer()}},{key:"initBreadboard",value:function(){this.breadboard=new r(this.config.breadboard),this.layers.push(this.breadboard)}},{key:"initConnections",value:function(){this.connections=new o({color:"#b2a474",width:10}),this.layers.push(this.connections)}},{key:"initDrawer",value:function(){this.drawer=new a}},{key:"load",value:function(t){this.connections.load([{x:10,y:10,title:"0"},{x:30,y:10,title:"1"},{x:10,y:30,title:"+"},{x:15,y:50,title:"-"}]),this.drawer.load(t,this.layers),this.drawer.start()}},{key:"layout",value:function(t){this.steps=t.map(function(t){var i=t.pins.reduce(function(t,i){return t[i.bank]=t[i.bank]||{rows:[]},t[i.bank].rows.push(i.row),t},{});return{banks:Object.keys(i).map(function(t){return{index:t,rows:i[t].rows}})}})}},{key:"next",value:function(){var t=++this.activeStepIndex>=this.steps.length?this.activeStepIndex=0:this.activeStepIndex;this.breadboard.highlight(this.steps[t].banks)}},{key:"prev",value:function(){var t=--this.activeStepIndex<0?this.activeStepIndex=this.steps.length-1:this.activeStepIndex;this.breadboard.highlight(this.steps[t].banks),console.log(this.steps[t])}},{key:"parseCircuit",value:function(t){}}]),t}();t.exports=s},function(t,i,n){"use strict";var e=new(n(5))({breadboard:{banks:[{offsetX:0,offsetY:0,width:4,height:480,topMargin:30,leftMargin:30,rows:3,vertical:!0,type:"TERMINAL"},{offsetX:80,offsetY:0,width:75,height:4,topMargin:30,leftMargin:30,rows:32},{offsetX:170,offsetY:0,width:75,height:4,topMargin:30,leftMargin:30,rows:32},{offsetX:275,offsetY:0,width:75,height:4,topMargin:30,leftMargin:30,rows:32}]}});window.addEventListener("DOMContentLoaded",function(){e.load("#burner");e.layout([{pins:[{bank:0,row:0,text:null,isRail:!0,isPowerRail:!1,isGPIO:!1,isGroundRail:!0}]},{pins:[{bank:0,row:2,text:null,isRail:!0,isPowerRail:!0,isGPIO:!1,isGroundRail:!1}]},{pins:[{bank:1,row:3,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1},{bank:1,row:3,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1}]},{pins:[{bank:1,row:5,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1},{bank:1,row:3,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1}]},{pins:[{bank:1,row:1,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1},{bank:0,row:0,text:null,isRail:!0,isPowerRail:!1,isGPIO:!1,isGroundRail:!0}]},{pins:[{bank:1,row:5,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1},{bank:1,row:1,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1}]},{pins:[{bank:1,row:4,text:"+",isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1},{bank:1,row:3,text:"-",isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1}]},{pins:[{bank:1,row:11,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1},{bank:1,row:6,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1}]},{pins:[{bank:1,row:6,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1},{bank:1,row:1,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1}]},{pins:[{bank:1,row:5,text:"Pole 1.1",isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1},{bank:1,row:5,text:"Pole 1.2",isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1},{bank:1,row:4,text:"Pole 1.3",isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1},{bank:1,row:5,text:"Coil 1",isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1},{bank:1,row:5,text:"Coil 2",isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1}]},{pins:[{bank:1,row:3,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1},{bank:0,row:0,text:null,isRail:!0,isPowerRail:!1,isGPIO:!1,isGroundRail:!0}]},{pins:[{bank:1,row:5,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1},{bank:0,row:2,text:null,isRail:!0,isPowerRail:!0,isGPIO:!1,isGroundRail:!1}]},{pins:[{bank:1,row:5,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1},{bank:1,row:11,text:null,isRail:!1,isPowerRail:!1,isGPIO:!1,isGroundRail:!1}]}])}),window.BurnerNew=e}]);