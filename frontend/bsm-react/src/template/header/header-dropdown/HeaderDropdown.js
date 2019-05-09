import React, { Component } from 'react';
import { compose } from 'redux';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { withTranslation } from "react-i18next";
import onClickOutside from 'react-onclickoutside';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

//components
import LinkButton from 'shared/components/buttons/link-button';
import Badge from 'shared/components/badge';
import DropdownTransition from 'shared/components/animations/dropdown-transition/DropdownTransition'

//utils
import joinClassNames from 'shared/utils/joinClassNames';

//selectors
import { getCurrentTheme } from 'shared/state/layout/selectors';

//styles
import styles from './HeaderDropdown.css';


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
        const { badge, icon, children, header, footer, className, theme } = this.props;
        const iconColor = theme === 'LIGHT_BLUE' ? 'white' : 'black';

        return (
            <li className={ className } >
                <LinkButton className={ styles.toggle } onClick={ () => this._onToggleClick() }>
                    <FontAwesomeIcon icon={ icon } color={ iconColor } size='lg'  />
                    { badge && <Badge>{ badge }</Badge> }
                </LinkButton>
                <DropdownTransition show={ this.state.isOpen } timeout={ 300 }>
                    <ul className={ styles.dropdownWrapper }>
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
                </DropdownTransition>
            </li>
        );
    }

    _onToggleClick() {
        this.setState(prevState => ({
            isOpen: !prevState.isOpen
        }) );
        this.props.enableOnClickOutside();
    }

    handleClickOutside = ( evt ) => {
        this.setState({ isOpen: false });
        this.props.disableOnClickOutside();
        console.log("clicked outside")
    };
}

HeaderDropdown.propTypes = {
    children: PropTypes.node,
    badge: PropTypes.number,
    icon: PropTypes.array,
    className: PropTypes.string,
    header: PropTypes.string,
    footer: PropTypes.string,
    theme: PropTypes.string,
};

const mapStateToProps = (state) => ({
    theme: getCurrentTheme(state),
});

export default connect(mapStateToProps)(onClickOutside(HeaderDropdown));
