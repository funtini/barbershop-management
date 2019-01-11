import React from 'react';
// import PropTypes from 'prop-types';
import joinClassNames from 'shared/utils/joinClassNames';

// Components.
import Loader from 'shared/components/loaders/loader/Loader';

// Styles.
import styles from './CSSLoaderOverlay.css';

const getClassNames = (isLoading, className) =>
    joinClassNames(
        styles.overlay,
        isLoading && styles.visible,
        className,
    );

const CSSLoaderOverlay = ({ children, isLoading, withLoader, className, loaderWrapperClassName }) => (
    <div className={ joinClassNames(styles.wrapper, className) }>
        { children }
        <div className={ getClassNames(isLoading, loaderWrapperClassName) }>
            { withLoader && <Loader /> }
        </div>
    </div>
);

// CSSLoaderOverlay.propTypes = {
//     children: PropTypes.node,
//     isLoading: PropTypes.bool,
//     withLoader: PropTypes.bool,
//     className: PropTypes.string,
//     loaderWrapperClassName: PropTypes.string,
// };

export default CSSLoaderOverlay;
