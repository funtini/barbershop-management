import React, { Component } from 'react';
import { compose } from 'redux';
import { connect } from 'react-redux';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { withTranslation } from 'react-i18next';
import get from 'lodash/get';
import joinClassNames from 'shared/utils/joinClassNames';

//components
import HeaderQuickActions from './header-quick-actions/HeaderQuickActions';
import HeaderNavigation from './header-navigation';

//state
import { getCurrentTheme, getSidebarStatus } from 'shared/state/layout/selectors';
import { istBreakpointLessThan } from 'shared/state/viewport/selectors';

//svg
import ShaveSvg from '../../shared/images/svg/shave.svg';
import BarberSvg from '../../shared/images/svg/barber.svg';

//styles
import styles from './Header.css'
import { expandSidebar } from 'shared/state/layout';

class Header extends Component {
    render() {
        const { onToggleClick, theme, className: headerStyle, isCollapsed, isMobile, t } = this.props;
        const svgColor = theme === 'LIGHT_BLUE' ? 'white' : 'black';

        return (
            <header className={ headerStyle } >
                <a href='/' className={ joinClassNames(styles.brand, isCollapsed && styles.collapsed ) }>
                    <span className={ !isCollapsed ? styles.miniLogo : '' }>
                        <b>{ t('brand.short-name') }</b>{ t('brand.short-suffix') }</span>
                    <span className={ joinClassNames(styles.largeLogo, isCollapsed && styles.miniLogo) }>
                        <BarberSvg width={33} height={33} fill={ svgColor }/>
                        <b> { t('brand.name') }</b> { t('brand.suffix') }
                </span>
                </a>

                <nav className={ joinClassNames(styles.navBar, isCollapsed && styles.expand) }>
                    <a href='/#' className={ styles.sidebarToggle } onClick={ onToggleClick }>
                    <FontAwesomeIcon icon={'bars'} />
                    </a>
                    <div className={ styles.navBarActions }>
                        <div className={ styles.leftContainer }>
                            <HeaderQuickActions isMobile={ isMobile }/>
                        </div>
                        <div className={ styles.rightContainer }>
                            <HeaderNavigation/>
                        </div>
                    </div>

                </nav>

            </header>
        );
    }
}

const mapStateToProps = (state) => ({
    theme: getCurrentTheme(state),
    isCollapsed: getSidebarStatus(state),
    isMobile: istBreakpointLessThan(state,'md'),
    // viewport: ge
});

export default compose(withTranslation(['header']),connect(mapStateToProps))(Header);
