import React, { Component } from 'react';

// components
import Layout from './template/layout';
import changeTheme from './shared/utils/changeTheme';
import { themeColors } from './shared/utils/changeTheme';

//styles
import styles from './App.css';

class App extends Component {
  render() {
    return (
      <div>
          <Layout>
            I WILL PUT ROUTES HERE
              <br/>
              <button onClick={this._handleClick}> Change Theme </button>
          </Layout>
      </div>
    );
  }

  _handleClick() {
      console.log('clicked me');
      changeTheme(themeColors.BLACK_WHITE);

  }
}

export default App;
