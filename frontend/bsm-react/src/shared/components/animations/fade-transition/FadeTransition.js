import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { CSSTransition } from 'react-transition-group';

import styles from './FadeTransition.css';

const transitionClassNames = ( prefix ) => ({
    enter: styles[`${prefix}Enter`],
    enterActive: styles[`${prefix}EnterActive`],
    exit: styles[`${prefix}Exit`],
    exitActive: styles[`${prefix}ExitActive`],
});

class FadeTransition extends Component {
    render() {
        const { children, show, timeout, fadeInSlideOut } = this.props;

        return (
            <CSSTransition
                in={ show }
                classNames={ transitionClassNames( fadeInSlideOut ? 'fadeSlide' : 'fade') }
                unmountOnExit
                timeout={ timeout } >
                { children }
            </CSSTransition>
        );
    }
}

FadeTransition.propTypes = {
    children: PropTypes.node,
    show: PropTypes.bool,
    timeout: PropTypes.number,
};

export default FadeTransition;
