import React, { Component } from 'react';
import { Route, Switch } from 'react-router';

// components
import Layout from './template/layout';
import changeTheme from './shared/utils/changeTheme';
import { themeColors } from './shared/utils/changeTheme';
import DashBoard from './pages/dashboard';
import Products from './pages/products';

// styles
import './App.css';

// icons
import './shared/icons';

class App extends Component {
  render() {
    return (
      <div>
          <Layout>
              <Switch>
                  <Route exact path="/" component={ DashBoard } />
                  <Route path="/products" component={ Products } />
                  <Route render={() => (<div>NOT FOUND PAGE</div>)} />
              </Switch>
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
