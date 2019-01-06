import React from 'react'

// styles
import styles from './content.css';

const content = ( props ) => (
    <section className={ styles.content }>
        {props.children}
    </section>
);

export default content;