package com.webi

/**
 * Created by IntelliJ IDEA.
 * User: suman
 * Date: 1/9/14.
 * Time: 11:31 AM
 */

//Sample file. Actual file is read from System environment variable path

email {
    to {
		//List of email Ids that need to receive the updates
        ids = [ 'xxxx@yyyy.com', 'zzzzz@yyyy.com' ]
    }
    from {
		//Email ID which will send the update. 
        id = 'xxxx@yyyy.com'
        password = 'YYYYYYY'
    }
}
//various logon credentials

boa {
    id = 'USERID'
    password = 'PASSWORD'
}

amex {
    id = 'USERID'
    password = 'PASSWORD'
}

macys {
    id = 'USERID'
    password = 'PASSWORD'
}

target {
    id = 'USERID'
    password = 'PASSWORD'
}

usaa {
    id = 'USERID'
    password = 'PASSWORD'
}