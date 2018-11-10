import React, { Component } from 'react'
import MenuItem from './menuItem'
import MenuTree from './menuTree'
import MenuHeader from './menuHeader';
import MenuNotification from './menuNotification';

export default class Menu extends Component {


    componentDidMount() {
        //Active classes on menu
        var menu = document.querySelector(".sidebar-menu");
        var items = menu.getElementsByClassName("menuItem");

        for (var i = 0; i < items.length; i++) {
            items[i].addEventListener("click", function () {
                var current = document.getElementsByClassName("active");
                console.log(current)
                if (this.offsetParent.className.includes('main-sidebar')) {
                    for (var i = 0; i < current.length; i++) {
                        if (current[i] && current[i].className.includes('tree'))
                        {
                            current[i].children[1].className = current[i].children[1].className.replace('menu-open', '')

                            
                            current[i].children[1].style.display = 'none'
                        }
                        current[i].className = current[i].className.replace(" active", "");
                       
                        
                    }
                }
                
                // console.log(current[0].children[1].className)
               

                for (var i = 0; i < current.length; i++) {
                    if (current[i] && !current[i].className.includes('tree') || (
                        this.offsetParent.className.includes('main-sidebar') && !this.className.includes('tree')
                    )) {
                        current[i].className = current[i].className.replace(" active", "");
                        console.log('ola')
                     
                    }

                }

                this.className += " active";
            });
        }
    }




    render() {
        return (
            <ul className='sidebar-menu'>
                <MenuHeader title="MAIN NAVIGATION" />
                <MenuTree label='Dashboard' icon='dashboard'>
                    <MenuItem path='#'
                        label='Today' icon='circle-o' />
                    <MenuItem path='#dashboard/business'
                        label='Business Summary' icon='circle-o' />
                </MenuTree>
                <MenuItem path='#booking' label='Booking' icon='calendar'>
                    <MenuNotification colour="blue" number="10" />
                    <MenuNotification colour="red" number="3" />
                </MenuItem>
                <MenuItem path='#customers' label='Customers' icon='users' />
                <MenuItem path='#products' label='Products' icon='cut' />
                <MenuTree label='Sales' icon='usd'>
                    <MenuItem path='#newSale'
                        label='Add' icon='usd' />
                </MenuTree>
                <MenuTree label='Business' icon='briefcase'>
                    <MenuItem path='#employers'
                        label='Employers' icon='circle-o' />
                    <MenuItem path='#expenses'
                        label='Expenses' icon='circle-o' />
                    <MenuItem path='#marketing'
                        label='Marketing' icon='circle-o' />
                    <MenuItem path='#control'
                        label='Control' icon='circle-o' />
                </MenuTree>
                <MenuTree label='Reports' icon='edit'>
                    <MenuItem path='#reports/current'
                        label='Current Month' icon='circle-o' />
                    <MenuItem path='#reports/all'
                        label='All Time' icon='circle-o' />
                </MenuTree>

                <MenuItem path='#settings' label='Settings' icon='cogs' />
                <MenuHeader title="DEVELOPTING MENU" />
                <MenuTree label='UI Components' icon='file'>
                    <MenuItem path='#widgets'
                        label='Widgets' icon='circle-o' />
                    <MenuItem path='#tables'
                        label='Tables' icon='circle-o' />
                    <MenuItem path='#buttons'
                        label='Buttons' icon='circle-o' />
                    <MenuItem path='#calendar'
                        label='Calendar' icon='circle-o' />
                    <MenuItem path='#charts'
                        label='Charts' icon='circle-o' />
                    <MenuItem path='#canvascharts'
                        label='CanvasCharts' icon='circle-o' />
                    <MenuItem path='#notifications'
                        label='Notifications' icon='circle-o' />
                    <MenuItem path='#forms'
                        label='Forms' icon='circle-o' />
                    <MenuItem path='#more'
                        label='More' icon='circle-o' />
                </MenuTree>
                <MenuTree label='UI Pages' icon='file'>
                    <MenuItem path='#profile'
                        label='Profile' icon='circle-o' />
                    <MenuItem path='#login'
                        label='Login' icon='circle-o' />
                    <MenuItem path='#lockscreen'
                        label='Lockscreen' icon='circle-o' />
                    <MenuItem path='#register'
                        label='Register' icon='circle-o' />
                    <MenuItem path='#error'
                        label='Error' icon='circle-o' />
                    <MenuItem path='#more'
                        label='More' icon='circle-o' />
                </MenuTree>
            </ul>
        )
    }
}

