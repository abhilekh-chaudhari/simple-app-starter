import React, { Component } from 'react';
import { Container } from 'reactstrap';
import DupChecker from '../components/DupChecker';

class DupCheckerPage extends Component {
  render() {
    return (
      <div className='app'>
        <div className='app-body'>
          <Container fluid className='text-center'>
            <DupChecker />
          </Container>
        </div>
      </div>
    );
  }
}

export default DupCheckerPage;
