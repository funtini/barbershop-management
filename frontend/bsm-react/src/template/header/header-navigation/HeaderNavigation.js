import React, { Component } from 'react'

//components
import HeaderDropdown from '../header-dropdown/HeaderDropdown';

//styles
import styles from './HeaderNavigation.css';

class HeaderNavigation extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
            //state data
            reduxData: [
                {
                    header: 'Message Box',
                    footer: 'View More',
                    icon: ['far','envelope'],
                    items:['Hello, can you book me a haircut?', 'A new report is ready to view!'],
                    unread: 1,
                },
                {
                    header: 'Notifications Box',
                    footer: 'View More',
                    icon: ['far','bell'],
                    items: ['Congratulations! Average Income has increased this week to 435€', 'Dont Forget! Some bookings need confirmation!'],
                    unread: undefined,
                }
            ],
        }
    }

    render(){
        return(
            <ul className={ styles.navBar }>
                { this.state.reduxData.map(this.renderDropdown) }
            </ul>
        )
    }

    renderDropdown = ({ header, footer, icon, unread, items }, index) => (
        <HeaderDropdown header={ header } footer={ footer } icon={ icon } className={ styles.option } badge={ unread } key={ index }>
            { items.map( (item, index) => <li key={ index }>{ item }</li> ) }
        </HeaderDropdown>
    );

}

export default HeaderNavigation;
