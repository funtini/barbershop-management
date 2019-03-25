import React, { Component } from 'react';
import { connect } from 'react-redux';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

//components
import HeaderQuickActions from './header-quick-actions/HeaderQuickActions';
import HeaderNavigation from './header-navigation';

//svg
import ShaveSvg from '../../shared/images/svg/shave.svg';
import BarberSvg from '../../shared/images/svg/barber.svg';

//styles
import styles from './Header.css'

class Header extends Component {
    render() {
        const { onToggleClick, ...rest } = this.props;

        return (
            <header { ...rest }>
                <a href='/' className={ styles.brand }>
                    <span className={ styles.miniLogo }>
                        <b>Bs</b>M</span>
                    <span className={ styles.largeLogo }>
                        <BarberSvg width={33} height={33} fill='white'/>
                        <b> BarberShop</b> Management
                </span>
                </a>

                <nav className={ styles.navBar }>
                    <a href='/#' className={ styles.sidebarToggle } onClick={ onToggleClick }>
                    <FontAwesomeIcon icon={'bars'} />
                    </a>
                    <div className={ styles.leftContainer }>
                        <HeaderQuickActions/>
                    </div>
                    <div className={ styles.rightContainer }>
                        <HeaderNavigation/>
                    </div>

                </nav>

            </header>
        );
    }
}

const mapStateToProps = (state) => {

}

export default connect(mapStateToProps)(Header);
