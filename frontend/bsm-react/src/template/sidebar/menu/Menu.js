import React, { Component } from 'react'
import { withTranslation } from 'react-i18next';

//components
import MenuHeader from './menu-header/MenuHeader';
import MenuTree from './menu-tree/MenuTree';
import MenuItem from './menu-item/MenuItem';
import menuOptions from './MenuOptions';

//styles
import styles from './Menu.css';

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
        const { t } = this.props;
        const options = menuOptions(t);

        return (
            <ul className={ styles.menu }>
                <MenuHeader title={ t('menu-header.main-navigation') }/>
                {
                    options && options.map((option)=> (
                        option.subItems ?
                            <MenuTree label={ option.item.label }
                                      icon={ option.item.icon }
                                      key={ option.item.label }
                                      onClick={ this._handleClickMenuItem }
                                      selected={ this.state.selected === option.item.label } >
                                {
                                    option.subItems.map((subItem) => (
                                        <MenuItem label={ subItem.label } icon={ subMenuIcon } key={ subItem.label }/>
                                    ))
                                }
                            </MenuTree>
                            :
                            <MenuItem label={ option.item.label }
                                      icon={ option.item.icon }
                                      key={ option.item.label }
                                      onClick={ this._handleClickMenuItem }
                                      selected={ this.state.selected === option.item.label } />

                    ))
                }
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
