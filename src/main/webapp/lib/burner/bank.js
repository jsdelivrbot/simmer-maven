const GraphicElement = require('./graphic-element');

module.exports = class Bank extends GraphicElement {
    constructor(config) {
        super();
        this.config = config;
        this.activeRows = {};
        this.rows = [];
    }

    draw(context) {
        this.rows.forEach((row, i) => {
            let rowColor = 'white';

            switch (row.type) {
                case 'GPIO':
                    rowColor = 'yellow';
                    break;
                case 'BUS':
                    // Hardcode power rail
                    if (i === 2) {
                        rowColor = 'red';
                    }
                    break;
            }

            context.rect(row.x, row.y, row.width, row.height, {
                background: row.active ? rowColor : 'black'
            });

            context.text(row.number, row.x - 5, row.y, {
                color: row.active ? rowColor : 'black',
                font: '10px Serif',
                align: 'center',
            });
        });
    }

    update() {
        const thickness = this.config.thickness || 3;
        const pitch = this.config.pitch || 15;
        let width = 0;
        let height = 0;
        let rowCount = 0;
        let rowOffset = 0;

        if (this.config.vertical) {
            width = thickness;
            height = pitch * (this.config.rowCount - this.config.rowOffset);
            rowCount = this.config.rows;
            rowOffset = 0;
        } else {
            width = pitch * 5;
            height = thickness;
            rowCount = this.config.rowCount;
            rowOffset = this.config.rowOffset;
        }

        const x = this.config.leftMargin + this.config.offsetX;
        const y = this.config.topMargin + this.config.offsetY;
        this.rows = [];

        for (let i = rowOffset; i < rowCount; i++) {
            let offset = (i - rowOffset) * pitch;
            let x1 = this.config.vertical ? x + offset : x;
            let y1 = this.config.vertical ? y : y + offset;
            let w = width;
            let h = height;

            x1 -= thickness / 2;
            y1 -= thickness / 2;

            this.rows.push({
                number: i + 1,
                x: Math.floor(x1),
                y: Math.floor(y1),
                width: w,
                height: h,
                type: this.config.type,
                active: this.activeRows[i]
            });
        }
    }

    highlight(rows = []) {
        this.activeRows = rows.reduce((accumulator, current) => {
            accumulator[current] = true;
            return accumulator;
        }, {});

        this.setDirty(true);
    }

    highlightAll(value) {
        for (let i = 0; i < this.config.rowCount; i++) {
            this.activeRows[i] = value;
        }

        this.setDirty(true);
    }

    getRows(indexes = []) {
        return this.rows.filter((row, i) => indexes.contains(i));
    }
};