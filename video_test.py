# Imports the monkeyrunner modules used by this program
from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice

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
# Takes a screenshot
result = device.takeSnapshot()

# Writes the screenshot to a file
result.writeToFile('./VideoshotMain.png','png')



# action 2 , click video list
device.touch(160,270,'DOWN_AND_UP')
MonkeyRunner.sleep(3)
result = device.takeSnapshot()
result.writeToFile('./VideoshotBabyClick.png','png')

# action 3 play music
MonkeyRunner.sleep(5)
'''
# action 4 pause music
device.touch(153,616,'DOWN_AND_UP')
MonkeyRunner.sleep(3)
result = device.takeSnapshot()
result.writeToFile('./shotStopClick.png','png')


# action 5 start playing music again
device.touch(232,616,'DOWN_AND_UP')
MonkeyRunner.sleep(5)
result = device.takeSnapshot()
result.writeToFile('./shotStartClick.png','png')

# action 6 stop playing music
device.touch(331,616,'DOWN_AND_UP')
MonkeyRunner.sleep(5)
result = device.takeSnapshot()
result.writeToFile('./shotStartClick.png','png')
'''
# action 7 press back key

device.press('KEYCODE_BACK','DOWN_AND_UP')
MonkeyRunner.sleep(3)
result = device.takeSnapshot()
result.writeToFile('./VideoshotBackClick.png','png')



# action 8 click remote layout

device.touch(408,151,'DOWN_AND_UP')
MonkeyRunner.sleep(1)
result = device.takeSnapshot()
result.writeToFile('./VideoshotRemoteClick.png','png')

device.touch(190,216,'DOWN_AND_UP')
MonkeyRunner.sleep(1)
result = device.takeSnapshot()
result.writeToFile('./VideoshotRemoteEditTextClick.png','png')


# action 9 input remote url
#device.shell("input text http://120.196.211.50/wap/645264.mp3")http://media.ch9.ms/ch9/2dea/b3fc154f-ce36-4c18-9356-bc5d0aa82dea/WP8JumpStart01a.mp4
device.type("http://www.w3schools.com/html/movie.mp4")

MonkeyRunner.sleep(10)
result = device.takeSnapshot()
result.writeToFile('./VideoshotRemoteInput.png','png')

# click edittext
device.touch(408,151,'DOWN_AND_UP')
MonkeyRunner.sleep(1)
result = device.takeSnapshot()
result.writeToFile('./VideoshotRemoteClick.png','png')

# action 10 click play button

device.touch(504,424,'DOWN_AND_UP')
MonkeyRunner.sleep(20)
result = device.takeSnapshot()
result.writeToFile('./VideoshotRemotePlayClick.png','png')


'''
# action 11 pause music
device.touch(153,616,'DOWN_AND_UP')
MonkeyRunner.sleep(3)
result = device.takeSnapshot()
result.writeToFile('./shotStopRemoteClick.png','png')

# action 12 press back key

device.press('KEYCODE_BACK','DOWN_AND_UP')
MonkeyRunner.sleep(1)
result = device.takeSnapshot()
result.writeToFile('./shotRemoteBackClick.png','png')


'''