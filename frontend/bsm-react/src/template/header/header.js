import React, { Component } from 'react';

//styles
import styles from './header.css'

class Header extends Component {
    render() {
        return (
            <header className={ styles.header } { ...this.props }>
                <a href='/' className={ styles.logo }>
                    LOGO
                    <span className='logo-mini'><b>Bs</b>M</span>
                    <span className='logo-lg'>
                {/*<i className='fa fa-bar-chart-o'></i>*/}
                        <b> BarberShop</b> Management
                </span>
                </a>

                <nav className={ styles.navBar }>
                    <a href='/' className={ styles.sidebarToggle }/>
                    NavBar
                </nav>

            </header>
        );
    }
}

export default Header;