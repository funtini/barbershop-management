import React, { Component } from 'react';
import { compose } from 'redux';
import { connect } from 'react-redux';
import { withTranslation } from 'react-i18next';

//components
import MenuHeader from './menu-header/MenuHeader';
import MenuTree from './menu-tree/MenuTree';
import MenuItem from './menu-item/MenuItem';
import menuOptions from './MenuOptions';

//styles
import styles from './Menu.css';
import { getSidebarStatus } from 'shared/state/layout/selectors';
import { expandSidebar } from 'shared/state/layout';

const subMenuIcon = ["far","circle"];

class Menu extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
            selected: '',
        };

        this._handleClickMenuItem = this._handleClickMenuItem.bind(this);
    }

    render () {
        const { t, isCollapsed } = this.props;
        const options = menuOptions(t);

        return (
            <ul className={ styles.menu }>
                { !isCollapsed && <MenuHeader title={ t('menu-header.main-navigation') }/>}
                {
                    options && options.map((option)=> (
                        option.subItems ?
                            <MenuTree label={ option.item.label }
                                      icon={ option.item.icon }
                                      key={ option.item.label }
                                      onClick={ this._handleClickMenuItem }
                                      isCollapsed={ isCollapsed }
                                      selected={ this.state.selected === option.item.label } >
                                {
                                    option.subItems.map((subItem) => (
                                        <MenuItem label={ subItem.label } icon={ subMenuIcon } isCollapsed={ isCollapsed } key={ subItem.label }/>
                                    ))
                                }
                            </MenuTree>
                            :
                            <MenuItem label={ option.item.label }
                                      icon={ option.item.icon }
                                      key={ option.item.label }
                                      onClick={ this._handleClickMenuItem }
                                      isCollapsed={ isCollapsed }
                                      selected={ this.state.selected === option.item.label } />

                    ))
                }
            </ul>
        )
    }

    _handleClickMenuItem(item, isTreeMenu) {
        const { isCollapsed, collapseSidebar } = this.props;

        if (isCollapsed && isTreeMenu){
            collapseSidebar();
        }

        this.setState({
            selected: item
        });
    }
}

const mapDispatchToProps = (dispatch) => ({
    collapseSidebar: () => dispatch(expandSidebar())
});

const mapStateToProps = (state) => ({
    isCollapsed: getSidebarStatus(state),
});

export default compose(withTranslation(['sidebar']),connect(mapStateToProps,mapDispatchToProps))(Menu);
