import React, { Component } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

//components
import HeaderQuickActions from './header-quick-actions/headerQuickActions';

//svg
import ShaveSvg from '../../shared/images/svg/shave.svg';
import BarberSvg from '../../shared/images/svg/barber.svg';

//styles
import styles from './header.css'

class Header extends Component {
    render() {
        return (
            <header { ...this.props }>
                <a href='/' className={ styles.brand }>
                    <span className={ styles.miniLogo }>
                        <b>Bs</b>M</span>
                    <span className={ styles.largeLogo }>
                        {/*<BarberSvg width={33} height={33} fill='white'/>*/}
                        {/*<b> BarberShop</b> Management*/}
                </span>
                </a>

                <nav className={ styles.navBar }>
                    <a href='/' className={ styles.sidebarToggle }>
                    <FontAwesomeIcon icon={'bars'} />
                    </a>
                    <div className={ styles.leftContainer }>
                        <HeaderQuickActions/>
                    </div>
                    <div className={ styles.rightContainer }>
                        NavBar Top Right
                    </div>

                </nav>

            </header>
        );
    }
}

export default Header;
