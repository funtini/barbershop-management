import React, { Component } from 'react';
import PropTypes from 'prop-types';
import onClickOutside from 'react-onclickoutside';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

//components
import LinkButton from "../../../shared/components/buttons/link-button";
import Badge from "../../../shared/components/badge";

//styles
import styles from './HeaderDropdown.css';
import joinClassNames from "../../../shared/utils/joinClassNames";

class HeaderDropdown extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
            isOpen: false,
        }
        //state data
    }

    render() {
        const dropdownClass = joinClassNames(
            styles.dropdownWrapper,
            !this.state.isOpen && styles.collapse,
            this.state.isOpen && styles.open,
        );

        const { badge, icon, children, header, footer, className } = this.props;

        return (
            <li className={ className } >
                <LinkButton className={ styles.toggle } onClick={ () => this._onToggleClick() }>
                    <FontAwesomeIcon icon={ icon } inverse/>
                    { badge && <Badge>{ badge }</Badge> }
                </LinkButton>
                <div>
                    <ul className={ dropdownClass }>
                        <li className={ styles.header }>
                            { header }
                        </li>
                        <li>
                            <ul className={ styles.options }>
                                { children }
                            </ul>
                        </li>
                        <li className={ styles.footer }>
                            <LinkButton className={ styles.link }>
                                { footer }
                            </LinkButton>
                        </li>
                    </ul>
                </div>
            </li>
        );
    }

    _onToggleClick() {
        this.setState(prevState => ({
            isOpen: !prevState.isOpen
        }) );
        this.props.enableOnClickOutside();
    }

    handleClickOutside = evt => {
        this.setState({ isOpen: false });
        this.props.disableOnClickOutside();
        console.log("clicked outside")
    };
}



HeaderDropdown.propTypes = {
    children: PropTypes.node,
    badge: PropTypes.number,
    icon: PropTypes.string,
    className: PropTypes.string,
    header: PropTypes.string,
    footer: PropTypes.string,
};

export default onClickOutside(HeaderDropdown);