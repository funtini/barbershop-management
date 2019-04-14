import React, { Component } from 'react';
import { connect } from 'react-redux';

// template components
import Header from 'template/header';
import SideBar from 'template/sidebar';
import Footer from 'template/footer';

//utils
import joinClassNames from 'shared/utils/joinClassNames';

// styles
import styles from './Layout.css';
import { expandSidebar } from 'shared/state/layout';
import { getSidebarStatus } from 'shared/state/layout/selectors';
import { istBreakpointLessThan } from 'shared/state/viewport/selectors';

class Layout extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
        }
        //state data
        this._handleToggleClick = this._handleToggleClick.bind(this);
    }

    componentDidMount() {
        if (this.props.isTablet && !this.props.isCollapsed){
            this.props.collapseSidebar();
        }
    }

    componentDidUpdate(prevProps) {
        if (!prevProps.isTablet && this.props.isTablet && !this.props.isCollapsed){
            this.props.collapseSidebar();
        }

        if (prevProps.isTablet && !this.props.isTablet && this.props.isCollapsed){
            this.props.collapseSidebar();
        }
    }

    render() {
        const { isCollapsed, children} = this.props;

        return (
            <div className={ styles.wrapper }>
                //TODO: backdrop state
                { false && <div className={ styles.backdrop }/> }
                <Header className={ styles.header } onToggleClick={ this._handleToggleClick }/>
                <SideBar className={ joinClassNames( styles.sidebar, isCollapsed && styles.collapsed )} />
                <main className={ joinClassNames( styles.content, isCollapsed && styles.expand ) }>
                    <div className={ styles.contentWrapper }>
                        { children }
                    </div>
                </main>
                <Footer className={ joinClassNames( styles.footer, isCollapsed && styles.expand) } />
            </div>
        )
    }

    _handleToggleClick() {
        this.props.collapseSidebar();
    }
}

const mapDispatchToProps = (dispatch) => ({
    collapseSidebar: () => dispatch(expandSidebar())
});

const mapStateToProps = (state) => ({
    isCollapsed: getSidebarStatus(state),
    isMobile: istBreakpointLessThan(state,'md'),
    isTablet: istBreakpointLessThan(state,'lg'),
});

export default connect(mapStateToProps,mapDispatchToProps)(Layout);
