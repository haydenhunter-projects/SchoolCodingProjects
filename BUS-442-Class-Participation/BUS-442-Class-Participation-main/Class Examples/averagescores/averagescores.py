# Form implementation generated from reading ui file 'averagescores.ui'
#
# Created by: PyQt6 UI code generator 6.7.1
#
# WARNING: Any manual changes made to this file will be lost when pyuic6 is
# run again.  Do not edit this file unless you know what you are doing.


from PyQt6 import QtCore, QtGui, QtWidgets
from PyQt6.QtWidgets import QMessageBox


class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(800, 600)
        self.centralwidget = QtWidgets.QWidget(parent=MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.lblStudentScores = QtWidgets.QLabel(parent=self.centralwidget)
        self.lblStudentScores.setGeometry(QtCore.QRect(590, 90, 151, 16))
        self.lblStudentScores.setObjectName("lblStudentScores")
        self.btnAddScore = QtWidgets.QPushButton(parent=self.centralwidget)
        self.btnAddScore.setGeometry(QtCore.QRect(370, 150, 91, 23))
        self.btnAddScore.setObjectName("btnAddScore")
        self.lstScores = QtWidgets.QListWidget(parent=self.centralwidget)
        self.lstScores.setGeometry(QtCore.QRect(585, 121, 181, 321))
        self.lstScores.setObjectName("lstScores")
        self.lblScore = QtWidgets.QLabel(parent=self.centralwidget)
        self.lblScore.setGeometry(QtCore.QRect(190, 90, 151, 16))
        self.lblScore.setObjectName("lblScore")
        self.txtStudentScore = QtWidgets.QLineEdit(parent=self.centralwidget)
        self.txtStudentScore.setGeometry(QtCore.QRect(350, 90, 121, 20))
        self.txtStudentScore.setObjectName("txtStudentScore")
        self.btnClear = QtWidgets.QPushButton(parent=self.centralwidget)
        self.btnClear.setGeometry(QtCore.QRect(370, 260, 91, 23))
        self.btnClear.setObjectName("btnClear")
        self.btnAverageScore = QtWidgets.QPushButton(parent=self.centralwidget)
        self.btnAverageScore.setGeometry(QtCore.QRect(370, 210, 91, 23))
        self.btnAverageScore.setObjectName("btnAverageScore")
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QtWidgets.QMenuBar(parent=MainWindow)
        self.menubar.setGeometry(QtCore.QRect(0, 0, 800, 22))
        self.menubar.setObjectName("menubar")
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QtWidgets.QStatusBar(parent=MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

        # connect the add score button to the add score function
        self.btnAddScore.clicked.connect(self.add_score)
        # connect the average score button to the calcuate average function
        self.btnAverageScore.clicked.connect(self.calculate_average)
        # connect the clear button to the clear function
        self.btnClear.clicked.connect(self.clear)

        # create an empty list to store student scores
        self.student_scores_list = []

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "MainWindow"))
        self.lblStudentScores.setText(_translate("MainWindow", "Student Scores"))
        self.btnAddScore.setText(_translate("MainWindow", "Add Score"))
        self.lblScore.setText(_translate("MainWindow", "Enter Student Score"))
        self.btnClear.setText(_translate("MainWindow", "Clear"))
        self.btnAverageScore.setText(_translate("MainWindow", "Average Score"))

    def add_score(self):

        if(self.txtStudentScore.text().isnumeric()):
            # Convert the string input to a float
            student_score = float(self.txtStudentScore.text())
            # Append the new score to the list
            self.student_scores_list.append(student_score)
            # Add the score to the list widget
            self.lstScores.addItem(str(student_score))

            self.txtStudentScore.clear()
        else:
            # Create a new message instance
            msg = QMessageBox()
            msg.setWindowTitle("Average Scores")
            msg.setText("Invalid student score")
            msg.exec()

            self.txtStudentScore.clear()


    def calculate_average(self):
        # Create a new message instance
        msg = QMessageBox()
        msg.setWindowTitle("Average Scores")
        
        if(len(self.student_scores_list) > 0):
            average_value = sum(self.student_scores_list) / len(self.student_scores_list)
            msg.setText("Average of all scores: " + str(average_value))
        else:
            msg.setText("No student scores added")    
            
        msg.exec()

    def clear(self):
        self.txtStudentScore.clear()
        self.lstScores.clear()
        
        # clear the list
        self.student_scores_list.clear()

if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    MainWindow = QtWidgets.QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(MainWindow)
    MainWindow.show()
    sys.exit(app.exec())
