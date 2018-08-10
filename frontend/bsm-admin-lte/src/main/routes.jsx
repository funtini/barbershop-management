import React from 'react'
import { Router, Route, IndexRoute, Redirect, hashHistory } from 'react-router'

import App from './app'
import Dashboard from '../components/dashboard'
import Sales from '../components/sales'
import Widgets from '../components/ui/widgets';
import Tables from '../components/ui/tables';
import Calendar from '../components/ui/calendar'
import Notifications from '../components/ui/notifications'
import Buttons from '../components/ui/buttons'
import Charts from '../components/ui/charts';
import CanvasCharts from '../components/ui/canvasCharts';
import Lockscreen from '../components/lockscreen/lockscreen';
import Customers from '../components/customers';
import More from '../components/ui/more';
import Forms from '../components/ui/forms';

export default props => (
    <Router history={hashHistory}>
        <Route path='/' component={Dashboard}/>
            
        <Route path='newSale' component={Sales} />
        <Route path='widgets' component={Widgets} />
        <Route path='tables' component={Tables} />
        <Route path='calendar' component={Calendar} />
        <Route path='notifications' component={Notifications} />
        <Route path='buttons' component={Buttons} />
        <Route path='charts' component={Charts} />
        <Route path='canvascharts' component={CanvasCharts} />
        <Route path='lockscreen' component={Lockscreen} />
        <Route path='customers' component={Customers} />
        <Route path='more' component={More} />
        <Route path='forms' component={Forms} />
        <Redirect from='*' to='/' />
    </Router>
)