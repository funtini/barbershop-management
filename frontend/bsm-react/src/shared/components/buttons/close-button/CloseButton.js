import React from 'react';
import PropTypes from 'prop-types';

// Utils
import joinClassNames from 'shared/utils/joinClassNames';

// Components.
import Close from 'shared/assets/icons/close/Close';

// Styles.
import styles from './CloseButton.css';

const CloseButton = ({ className, onClick }) => (
    <button className={ joinClassNames(styles.closeButton, className) } onClick={ onClick }>
        <Close/>
    </button>
);

CloseButton.propTypes = {
    className: PropTypes.string,
    onClick: PropTypes.func,
};

export default CloseButton;
