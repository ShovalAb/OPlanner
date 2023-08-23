import React from 'react';

const TabsSidebar = ({ tabs, activeTab, onTabClick }) => {
  return (
    <div style={{ width: '200px', height: '60px', position: 'sticky', top: 0}}>
        <div style={{color: 'lightblue'}}>
            <div style={{marginTop: '0px'}}>
            {tabs.map((tab, index) => (
                <button
                key={index}
                onClick={() => onTabClick(tab.value)}
                style={{
                    width: '100%', // Set the fixed tab width
                    height: '60px', // Set the fixed tab height
                    marginBottom: '0px', // Add space between tabs
                    backgroundColor: activeTab === tab.value ? 'lightblue' : 'lightgray',
                    borderRightColor: 'black',
                    borderColor: 'transparent',
                    // borderRight: '1px solid black', // Add a divider line on the right
                    // borderBottom: 'none', // Remove default border
                    // borderTopRightRadius: index === 0 ? '15px' : '0', // Apply rounded corner to the first button
                    // borderBottomRightRadius: index === 0 ? '15px' : '0', // Apply rounded corner to the first button
                    // borderRadius: '0px', // Remove border radius
                    color: activeTab === tab.value ? 'white' : 'black',
                    boxShadow: activeTab === tab.value ? '0px 2px 4px rgba(0, 0, 0, 0.2)' : 'none', // Add shadow to chosen tab
                    zIndex: activeTab === tab.value ? '1' : '0', // Increase z-index for chosen tab
                }}
                >
                {tab.label}
                </button>
            ))}
            </div>
      </div>
    </div>
  );
};

export default TabsSidebar;
