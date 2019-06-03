import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

//components
import CloseIcon from 'shared/assets/icons/close/Close';
import CrossIcon from 'shared/assets/icons/cross/Cross';

//util
import joinClassNames from 'shared/utils/joinClassNames';

// styles
import styles from './Notification.css';

const icon = {
    success: {
        type:'check',
        color: 'dark-green',
        size: '2x',
    },
    info: {
        type: 'info',
        color: 'blue',
        size: '2x'
    },
    warning: {
        type: 'exclamation',
        color: 'dark-yellow',
        size: '2x'
    },
    danger: {
        type: 'times',
        color: 'red',
        size: '2x'
    }
};


const Notification = ( { children, className, type, ...rest } ) => (
    <div className={ joinClassNames(className, styles.notification, type && styles[type]) } { ...rest } >
        <div className={ styles.icon }>
            <FontAwesomeIcon icon={ icon[type].type } size={ icon[type].size } color={'white'}/>
        </div>
        <div className={ styles.content }>
            <p className={ styles.type }>Success</p>
            <p className={ styles.message }>Anyone with access can view your invited visitors.</p>
        </div>
        <div className={ styles.close }>
            <CrossIcon color={ icon[type].color } className={ styles.closeIcon }/>
        </div>
    </div>
);

export default Notification;
