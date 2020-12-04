import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import './App.scss';
import HelloPage from "./pages/HelloPage";
import HomePage from "./pages/HomePage";
import DupCheckerPage from "./pages/DupCheckerPage";

class App extends Component {
  render() {
    return (
      <Switch>
        <Route key="home" path="/" exact={true} component={HomePage} />
        <Route key="hello" path="/hello" exact={true} component={HelloPage} />
        <Route key="dup" path="/dup" exact={true} component={DupCheckerPage} />
      </Switch>
    );
  }
}

export default App;
