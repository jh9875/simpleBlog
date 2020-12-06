import React, { Component } from "react";
import "./App.css";
import Button from "./components/Button";

class App extends Component {
  render() {
    return (
      <div className="App">
        <h1 className="title">temp</h1>
        <Button name="name">test..</Button>
      </div>
    );
  }
}

export default App;
