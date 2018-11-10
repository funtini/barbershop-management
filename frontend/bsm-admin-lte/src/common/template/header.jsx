import React from 'react'
import UserHeader from './userHeader';
import HeaderNav from './headerNav';
import HeaderDropdown from './headerDropdown';
import DropdownInfo from './dropdownInfo';


export default props => (
    <header className='main-header'>
        <a href='/#/' className='logo'>
            <span className='logo-mini'><b>Bs</b>M</span>
            <span className='logo-lg'>
                <i className='fa fa-bar-chart-o'></i>
                <b> BarberShop</b> Management
            </span>
        </a>
        <nav className='navbar navbar-static-top'>
            <a href='/#/' className='sidebar-toggle' data-toggle='offcanvas'></a>
            <div className="navbar-top-left">
            <ul className="top-menu">
                <li>NEW SALE</li>
                <li>NEW CUSTOMER</li>
                <li>NEW BOOKING</li>
            </ul>
            </div>
            <HeaderNav>
            <HeaderDropdown type="notifications-menu" header="You have 10 messages" number="10" badge="warning" icon="bell-o" footer="View All" centered>
                <DropdownInfo icon="warning" color="yellow" text="Report of July is available to close"/>
                <DropdownInfo icon="users" color="aqua" text="You have a new record of sales"/>
                <DropdownInfo icon="clock-o" color="red" text="You are in time to close"/>
            </HeaderDropdown>    
            <UserHeader image={require('../../images/user1-128x128.jpg')} name="Pedro Domingos" role="CEO"/>
            <HeaderDropdown type="messages-menu" header="SESSION OPTIONS" icon="gears" footer="View All" centered>
                <DropdownInfo icon="warning" color="yellow" text="You have to create dropdown menu"/>
               
            </HeaderDropdown>  
            </HeaderNav>
        </nav>
    </header>
)