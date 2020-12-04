import React, { Component } from 'react';
import { getRefinedData } from "../actions/dupCheckerAction";

/**
 * DupChecker gets data and shows the Duplicates and 
 * Non-duplicates in separate tables.
 */
class DupChecker extends Component {
  constructor(props) {
    super(props);
    this.state = {
      apidata: []
    };
  }

  componentDidMount() {
    this._isMounted = true;
    getRefinedData().then( data => {
      if (this._isMounted)
        this.setState({apidata: data});
    }).catch(() => {
      if (this._isMounted)
        this.setState({message: 'The server did not respond so...hello from the client!'});
    });
  }

  componentWillUnmount() {
    this._isMounted = false;
  }

  render() {
    return (
      <div>
        {Object.keys(this.state.apidata).map((recordList, i) => (
        <div key={recordList}>
          <h2>{recordList}</h2>
          <table border={1} cellPadding={1}>
           <thead>
              <tr>
                <td>id</td>
                <td>First Name</td>
                <td>Last Name</td>
                <td>Company</td>
                <td>Email</td>
                <td>Address1</td>
                <td>Address2</td>
                <td>Zip</td>
                <td>City</td>
                <td>State_Long</td>
                <td>State</td>
                <td>Phone</td>
              </tr>
           </thead>
           <tbody>
           {this.state.apidata[recordList].map((record, i) => (
              <tr>
              <td>{record["id"]}</td>
              <td>{record["first_name"]}</td>
              <td>{record["last_name"]}</td>
              <td>{record["company"]}</td>
              <td>{record["email"]}</td>
              <td>{record["address1"]}</td>
              <td>{record["address2"]}</td>
              <td>{record["zip"]}</td>
              <td>{record["city"]}</td>
              <td>{record["state_long"]}</td>
              <td>{record["state"]}</td>
              <td>{record["phone"]}</td>
            </tr>
            ))}
           </tbody>
        </table>
        </div>
        ))}
      </div>

    );
  }
}

export default DupChecker;
