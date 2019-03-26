import React, { Component } from 'react';
import { connect } from 'react-redux';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import get from 'lodash/get';

//components
import HeaderQuickActions from './header-quick-actions/HeaderQuickActions';
import HeaderNavigation from './header-navigation';

//state
import { getCurrentTheme } from 'shared/state/layout/selectors';

//svg
import ShaveSvg from '../../shared/images/svg/shave.svg';
import BarberSvg from '../../shared/images/svg/barber.svg';

//styles
import styles from './Header.css'

class Header extends Component {
    render() {
        //TODO: improve this props extraction, to remove dispatch from here
        const { onToggleClick, theme, dispatch, ...rest } = this.props;
        const svgColor = theme === 'LIGHT_BLUE' ? 'white' : 'black';

        return (
            <header { ...rest }>
                <a href='/' className={ styles.brand }>
                    <span className={ styles.miniLogo }>
                        <b>Bs</b>M</span>
                    <span className={ styles.largeLogo }>
                        <BarberSvg width={33} height={33} fill={ svgColor }/>
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

const mapStateToProps = (state) => ({
    theme: getCurrentTheme(state),
});

export default connect(mapStateToProps)(Header);
