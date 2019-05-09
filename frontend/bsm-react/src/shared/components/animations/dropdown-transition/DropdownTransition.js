import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { CSSTransition } from 'react-transition-group';

import styles from './DropdownTransition.css';

const transitionClassNames = ( prefix ) => ({
    enter: styles[`${prefix}Enter`],
    enterActive: styles[`${prefix}EnterActive`],
    exit: styles[`${prefix}Exit`],
    exitActive: styles[`${prefix}ExitActive`],
});

class DropdownTransition extends Component {
    render() {
        const { children, show, timeout } = this.props;

        return (
            <CSSTransition
                in={ show }
                classNames={ transitionClassNames('dropdown') }
                unmountOnExit
                timeout={ timeout } >
                { children }
            </CSSTransition>
        );
    }
}

DropdownTransition.propTypes = {
    children: PropTypes.node,
    show: PropTypes.bool,
    timeout: PropTypes.number,
};

export default DropdownTransition;
