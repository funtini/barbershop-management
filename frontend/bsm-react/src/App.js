import React, { Component } from 'react';
import { library } from '@fortawesome/fontawesome-svg-core'

// components
import Layout from './template/layout';
import changeTheme from './shared/utils/changeTheme';
import { themeColors } from './shared/utils/changeTheme';
import DashBoard from './pages/dashboard';

//styles
import styles from './App.css';

//icons
import { fab } from '@fortawesome/free-brands-svg-icons'
import { faCheckSquare, faCoffee, faBars, faEnvelope, faFlag, faAngleLeft, faCircle, faTachometerAlt } from '@fortawesome/free-solid-svg-icons'

library.add(fab, faCheckSquare, faCoffee, faBars, faEnvelope, faFlag, faAngleLeft, faCircle, faTachometerAlt);

class App extends Component {
  render() {
    return (
      <div>
          <Layout>
              <DashBoard/>
              {/*<br/>*/}
              {/*<button onClick={this._handleClick}> Change Theme </button>*/}
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
