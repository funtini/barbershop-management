import React, { Component } from 'react'
import { withTranslation } from 'react-i18next';

//components
import MenuHeader from './menu-header/MenuHeader';
import MenuTree from './menu-tree/MenuTree';
import MenuItem from './menu-item/MenuItem';

//styles
import styles from './Menu.css';

const subMenuIcon = 'circle';

//TODO: css animations to variables, make it standard for all animations and dropdown
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
        const { t } = this.props;

        return (
            <ul className={ styles.menu }>
                <MenuHeader title={ t('menu-header.main-navigation') }/>
                <MenuTree label={ t('menu-option.dashboard') }
                          icon={'tachometer-alt'}
                          onClick={ this._handleClickMenuItem }
                          selected={ this.state.selected === t('menu-option.dashboard') } >
                    <MenuItem label={ 'Today' } icon={ subMenuIcon }/>
                </MenuTree>
                <MenuItem label={ t('menu-option.booking') }
                          icon={'tachometer-alt'}
                          onClick={ this._handleClickMenuItem }
                          selected={ this.state.selected === t('menu-option.booking')} />
                <MenuItem label={ t('menu-option.customers') }
                          icon={'tachometer-alt'}
                          onClick={ this._handleClickMenuItem }
                          selected={ this.state.selected === t('menu-option.customers')} />
                <MenuItem label={ t('menu-option.products') }
                          icon={'tachometer-alt'}
                          onClick={ this._handleClickMenuItem }
                          selected={ this.state.selected === t('menu-option.products')} />
                <MenuTree label={ t('menu-option.settings') }
                          icon={'tachometer-alt'}
                          onClick={ this._handleClickMenuItem }
                          selected={ this.state.selected === t('menu-option.settings') }>
                    <MenuItem label={ 'Option 1' } icon={ subMenuIcon }/>
                    <MenuItem label={ 'Option 2' } icon={ subMenuIcon }/>
                    <MenuItem label={ 'Option 3' } icon={ subMenuIcon }/>
                </MenuTree>
            </ul>
        )
    }

    _handleClickMenuItem(item) {
        this.setState({
            selected: item
        });
    }
}

export default withTranslation('sidebar')(Menu);
