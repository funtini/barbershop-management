import React from 'react';
import PropTypes from 'prop-types';

// utils
import joinClassNames from 'shared/utils/joinClassNames';

// Styles.
import styles from './Button.css';

const getClassNames = (role, disabled, modifier, isTouchDevice, wide, className) =>
    joinClassNames(
        styles.button,
        !isTouchDevice && styles.hasHover,
        styles[role],
        disabled && styles.disabled,
        modifier && styles[modifier],
        wide && styles.wide,
        className
    );

const Button = ({ role, disabled, className, children, modifier, wide, isTouchDevice, ...props }) => (
    <button className={ getClassNames(role, disabled, modifier, isTouchDevice, wide, className) } disabled={ disabled } { ...props }>
        { children }
    </button>
);

Button.propTypes = {
    role: PropTypes.oneOf(['primary', 'secondary', 'unboxed']),
    modifier: PropTypes.string,
    type: PropTypes.string,
    disabled: PropTypes.bool,
    isTouchDevice: PropTypes.bool,
    className: PropTypes.string,
    children: PropTypes.node,
};

Button.defaultProps = {
    disabled: false,
};

export default Button;
