import React from 'react'

//styles
import styles from './contentTitle.css';

const contentTitle = (props ) => (
    <h2 className={ styles.titleContainer }>
        {props.title}
        <small>
            {props.small}
        </small>
    </h2>
);

export default contentTitle;