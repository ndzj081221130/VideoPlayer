from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice
from com.android.monkeyrunner.easy import EasyMonkeyDevice
from com.android.monkeyrunner.easy import By
from com.android.chimpchat.hierarchyviewer import HierarchyViewer
from com.android.hierarchyviewerlib.device import ViewNode


# Connects to the current device, returning a MonkeyDevice object
device = MonkeyRunner.waitForConnection()

# Installs the Android package. Notice that this method returns a boolean, so you can test
# to see if the installation worked.
flag = device.installPackage('/Users/zhang/Documents/workspace_backup/VideoPlayerRepo/bin/VideoPlayerRepo.apk')

print "install flag= %s" % flag

# sets a variable with the package's internal name
package = 'com.simon.video'

# sets a variable with the name of an Activity in the package
activity = 'com.simon.video.MainActivity'

# sets the name of the component to start
runComponent = package + '/' + activity

# Runs the component
flag = device.startActivity(component=runComponent)
print "open mainActivity flag= %s" % flag
# Presses the Menu button
#device.press('KEYCODE_MENU', MonkeyDevice.DOWN_AND_UP)

MonkeyRunner.sleep(3)


hierarchy_viewer = device.getHierarchyViewer()
list = hierarchy_viewer.findViewById('id/local_list')

childList = list.children

if childList:
    firstNode = childList[1]
    first = hierarchy_viewer.getAbsolutePositionOfView(firstNode)
    
    device.touch(first.x + 20,first.y+10,'DOWN_AND_UP')
    print "x= %d , y = %d " % (first.x , first.y)
    
    MonkeyRunner.sleep(10)

    device.press('KEYCODE_BACK','DOWN_AND_UP')
    MonkeyRunner.sleep(3)

    action_bar = hierarchy_viewer.findViewById('id/action_bar_container')
    scollingTab = action_bar.children[2]
    linearLayout = scollingTab.children[0]
    remoteLayout = linearLayout.children[1]
    
    remote = hierarchy_viewer.getAbsolutePositionOfView(remoteLayout)
    
    device.touch(remote.x , remote.y,'DOWN_AND_UP')
    print "remote  x= %d , y = %d " % (remote.x,remote.y)
    MonkeyRunner.sleep(3)
    
    textNode = hierarchy_viewer.findViewById('id/autoCompleteTextView')
    text = hierarchy_viewer.getAbsolutePositionOfView(textNode)
    device.touch(text.x,text.y,'DOWN_AND_UP')
    print "remote text x= %d , y = %d " % (text.x,text.y)
    MonkeyRunner.sleep(1)

    device.type("http://www.w3schools.com/html/movie.mp4")

    MonkeyRunner.sleep(10)
    textNode = hierarchy_viewer.findViewById('id/autoCompleteTextView')
    text = hierarchy_viewer.getAbsolutePositionOfView(textNode)
    device.touch(text.x,text.y,'DOWN_AND_UP')
    print "remote text x= %d , y = %d " % (text.x,text.y)
    MonkeyRunner.sleep(1)
            
    remote_playNode = hierarchy_viewer.findViewById('id/button1')
    remote_play = hierarchy_viewer.getAbsolutePositionOfView(remote_playNode)
            
    device.touch(remote_play.x , remote_play.y,'DOWN_AND_UP')
    print "remote Play x= %d , y = %d " % (remote_play.x,remote_play.y)
            
    MonkeyRunner.sleep(10)

    
    device.press('KEYCODE_BACK','DOWN_AND_UP')
    MonkeyRunner.sleep(3)
