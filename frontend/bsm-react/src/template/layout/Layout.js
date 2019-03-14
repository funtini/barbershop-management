import React, { useState } from 'react';

// template components
import Header from 'template/header';
import SideBar from 'template/sidebar';
import Footer from 'template/footer';

//utils
import joinClassNames from 'shared/utils/joinClassNames';

// styles
import styles from './Layout.css';

const layout = ( props ) => {
        const [toggle, setToggle] = useState(false);

        console.log(toggle)

        return (
            <div className={ styles.wrapper }>
                    <Header className={ styles.header } onToggleClick={ () => { setToggle(!toggle); _handleToggleClick(); } }/>
                    <SideBar className={ joinClassNames( styles.sidebar, toggle && styles.collapsed )} />
                    <main className={ joinClassNames( styles.content, toggle && styles.expand ) }>
                            <div className={ styles.contentWrapper }>
                                    { props.children }
                            </div>
                    </main>
                    <Footer className={ joinClassNames( styles.footer, toggle && styles.expand) } />
            </div>
        );
}

const _handleToggleClick = () => (
    console.log("IM CLICKING")
)

export default layout;
