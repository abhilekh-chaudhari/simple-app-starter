import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';

class HomePage extends Component {
  render() {
    return (
        <div className="home-page">
          <Link to="/hello">Click to see hello message</Link>
          <Link to="/dup">Click to see dup message</Link>
        </div>
    );
  }
}

export default withRouter(HomePage);
