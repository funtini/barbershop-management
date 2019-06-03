import React from 'react';
import PropTypes from 'prop-types';

import joinClassNames from 'shared/utils/joinClassNames';

// Styles.
import styles from './Cross.css';

const Cross = ({ className, color, ...props }) => (
    <div className={ joinClassNames(styles.wrapper, className, color && styles[color]) } { ...props }/>
);

Cross.propTypes = {
    className: PropTypes.string,
    onClick: PropTypes.func,
};

export default Cross;
