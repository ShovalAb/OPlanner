import React from 'react';

const TabsSidebar = ({ tabs, activeTab, onTabClick }) => {
  return (
    <div style={{ width: '200px', height: '60px', position: 'sticky', top: 0 }}>
        <div style={{color: 'lightblue'}}>
            <div style={{marginTop: '10px'}}>
            {tabs.map((tab, index) => (
                <button
                key={index}
                onClick={() => onTabClick(tab.value)}
                style={{
                    width: '100%', // Set the fixed tab width
                    height: '60px', // Set the fixed tab height
                    marginBottom: '0px', // Add space between tabs
                    backgroundColor: activeTab === tab.value ? 'lightblue' : 'white',
                    borderRightColor: 'black',
                    borderColor: 'transparent',
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
