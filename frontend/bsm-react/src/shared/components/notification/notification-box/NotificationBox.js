import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { withTranslation } from 'react-i18next';

//components
import CrossIcon from 'shared/assets/icons/cross/Cross';

//util
import joinClassNames from 'shared/utils/joinClassNames';

// styles
import styles from './NotificationBox.css';

const icon = {
    success: {
        type: 'check',
        color: 'var(--theme-color-success)',
        size: '2x',
    },
    info: {
        type: 'info-circle',
        color: 'var(--theme-color-info)',
        size: '2x'
    },
    warning: {
        type: 'exclamation-triangle',
        color: 'var(--theme-color-warning)',
        size: '2x'
    },
    danger: {
        type: 'exclamation-circle',
        color: 'var(--theme-color-danger)',
        size: 'lg'
    }
};

const getTitle = (type, translation) => {
    switch (type) {
        case 'danger':
            return translation('notification.error-title');
        case 'warning':
            return translation('notification.warning-title');
        case 'info':
            return translation('notification.info-title');
        case 'success':
            return translation('notification.success-title');
        default :
            return undefined;
    }
};

const NotificationBox = ( { children, className, type, title, close, t: translation } ) => (
    <div className={ joinClassNames(className, styles.notification, type && styles[type]) }>
        <div className={ styles.icon }>
            <FontAwesomeIcon icon={ icon[type].type } size={ icon[type].size } color={ icon[type].color }/>
        </div>
        <div className={ styles.content }>
            { getTitle(type,translation) && <p className={ styles.type }> { title ? title : getTitle(type,translation) } </p> }
            <p className={ styles.message }>{ children }</p>
        </div>
        <div className={ styles.close }>
            <CrossIcon color={ type } className={ styles.closeIcon } onClick={ close } />
        </div>
    </div>
);

export default withTranslation()(NotificationBox);