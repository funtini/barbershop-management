import React, { Component } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

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
        return (
            <ul className={ styles.menu }>
                <MenuHeader title={ 'MAIN NAVIGATION' }/>
                <MenuTree label={ 'Dashboard' } icon={'tachometer-alt'} onClick={ this._handleClickMenuItem } selected={ this.state.selected === 'Dashboard' } >
                    <MenuItem label={ 'Today' } icon={ subMenuIcon }/>
                </MenuTree>
                <MenuItem label={ 'Account' } icon={'tachometer-alt'} onClick={ this._handleClickMenuItem } selected={ this.state.selected === 'Account'} />
                <MenuTree label={ 'Settings' } icon={'tachometer-alt'} onClick={ this._handleClickMenuItem } selected={ this.state.selected === 'Settings' }>
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

export default Menu;
