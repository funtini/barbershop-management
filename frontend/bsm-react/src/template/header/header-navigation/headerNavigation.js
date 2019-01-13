import React, { Component } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

//components
import LinkButton from 'shared/components/buttons/link-button';
import Badge from 'shared/components/badge';

//styles
import styles from './HeaderNavigation.css';

class HeaderNavigation extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
            svgIcons: [],
        }
        //state data
    }

    render(){
        return(
            <ul className={styles.navBar}>
                { this._renderMessages() }
                { this._renderNotifications() }
            </ul>
        )
    }

    _renderMessages(){
        return(
            <li className={ styles.option }>
                <LinkButton className={ styles.link } onClick={ () => (console.log("CLICK"))}>
                    <FontAwesomeIcon icon={'envelope'} inverse/>
                    <Badge>1</Badge>
                </LinkButton>
            </li>
        )
    }

    _renderNotifications(){
        return(
            <li className={ styles.option }>
                <LinkButton className={ styles.link } onClick={ () => (console.log("CLICK"))}>
                    <FontAwesomeIcon icon={'flag'} />
                    {/*BADGE*/}
                </LinkButton>
            </li>
        )
    }
}

export default HeaderNavigation;