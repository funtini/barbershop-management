import React from 'react'
import { Router, Route, IndexRoute, Redirect, hashHistory } from 'react-router'

import App from './app'
import Dashboard from '../components/dashboard'
import Sales from '../components/sales'


export default props => (
    <Router history={hashHistory}>
        <Route path='/' component={Dashboard}/>
            
        <Route path='newSale' component={Sales} />
        
        <Redirect from='*' to='/' />
    </Router>
)