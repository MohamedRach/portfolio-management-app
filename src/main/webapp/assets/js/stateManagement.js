let instance;

class GlobalState {
    constructor() {
        if (instance) {
            return instance;
        }

        this.state = [];
        instance = this;
    }

    getState() {
        return this.state;
    }

    setState(data) {
        this.state = data;
    }
    updateState(portfolio){
        this.state = [...this.state, portfolio]
    }
}

const globalState = new GlobalState();
export {globalState}
