import React from 'react';
import PropTypes from 'prop-types';

// utils
import joinClassNames from 'shared/utils/joinClassNames';

// Styles.
import styles from './Button.css';

/**
 * ### BUTTONS TYPE ####
 *  ---------------------
 *      - Primary     --->   Normal Size // Primary Colors
 *      - Secondary   --->   Small Size // Primary Colors
 *      - Unboxed     --->   No box
 *
 *
 * ### MODIFIERS ####
 *  -----------------
 *
 *      - outlineButton     ---> White background
 *      - fillHoverButton   ---> Fill Primary color background on hover
 *
 */
const getClassNames = (role, disabled, modifier, isTouchDevice, wide, gradient, className) =>
    joinClassNames(
        styles.button,
        !isTouchDevice && styles.hasHover,
        styles[role],
        disabled && styles.disabled,
        modifier && styles[modifier],
        !modifier && styles.standart,
        wide && styles.wide,
        gradient && styles.withGradient,
        className
    );

const Button = ({ role, disabled, className, children, modifier, wide, gradient, isTouchDevice, ...props }) => (
    <button className={ getClassNames(role, disabled, modifier, isTouchDevice, wide, gradient, className) } disabled={ disabled } { ...props }>
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
