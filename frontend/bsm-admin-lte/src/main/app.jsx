import '../common/template/dependencies'
import React from 'react'

import Header from '../common/template/header'
import SideBar from '../common/template/sideBar'
import Footer from '../common/template/footer'
import Routes from './routes'
import Lockscreen from './../components/lockscreen/lockscreen'

export default props => (
    <div className='skin-blue fixed sidebar-mini'>
    <div className='wrapper'>
        <Header />
        <SideBar/>
        <div className='content-wrapper'>
            <Routes />
        </div>
        <Footer/>
    </div>
    </div>
    
    // <Lockscreen/>
  
)