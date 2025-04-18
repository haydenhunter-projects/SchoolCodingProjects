# Form implementation generated from reading ui file 'studentscores.ui'
#
# Created by: PyQt6 UI code generator 6.7.1
#
# WARNING: Any manual changes made to this file will be lost when pyuic6 is
# run again.  Do not edit this file unless you know what you are doing.


from PyQt6 import QtCore, QtGui, QtWidgets


class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(800, 600)
        self.centralwidget = QtWidgets.QWidget(parent=MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.lstStudentScores = QtWidgets.QListWidget(parent=self.centralwidget)
        self.lstStudentScores.setGeometry(QtCore.QRect(260, 140, 256, 192))
        self.lstStudentScores.setObjectName("lstStudentScores")
        self.txtAverageResult = QtWidgets.QLineEdit(parent=self.centralwidget)
        self.txtAverageResult.setGeometry(QtCore.QRect(390, 400, 113, 20))
        self.txtAverageResult.setReadOnly(True)
        self.txtAverageResult.setObjectName("txtAverageResult")
        self.lblAverageScore = QtWidgets.QLabel(parent=self.centralwidget)
        self.lblAverageScore.setGeometry(QtCore.QRect(280, 400, 91, 16))
        self.lblAverageScore.setObjectName("lblAverageScore")
        self.lblStudentScores = QtWidgets.QLabel(parent=self.centralwidget)
        self.lblStudentScores.setGeometry(QtCore.QRect(340, 110, 91, 16))
        self.lblStudentScores.setObjectName("lblStudentScores")
        self.btnCalcAvg = QtWidgets.QPushButton(parent=self.centralwidget)
        self.btnCalcAvg.setGeometry(QtCore.QRect(320, 350, 141, 23))
        self.btnCalcAvg.setObjectName("btnCalcAvg")
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


        self.student_scores_dictionary = self.load_data("student_scores.txt")

        self.btnCalcAvg.clicked.connect(self.calculate_average)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "MainWindow"))
        self.lblAverageScore.setText(_translate("MainWindow", "Average Score"))
        self.lblStudentScores.setText(_translate("MainWindow", "Student Scores"))
        self.btnCalcAvg.setText(_translate("MainWindow", "Calculate Average"))


    def load_data(self, file_name):
        # Create an empty dictionary named scores
        scores = {}
        with open(file_name, "r") as fname:
            for line in fname:
                # strip() will remove extra whitespaces before and after the text. split() will split based on the delimiter
                name, score = line.strip().split(",")
                # Add a new key value pair to the dictionary
                scores[name] = int(score)
                # Write the name and score to the list box
                self.lstStudentScores.addItem(str(name) + "    " + str(score))
        return scores
    
    def calculate_average(self):
        total = 0 
        length = len(self.student_scores_dictionary)
        for key,value in self.student_scores_dictionary.items():
            total = total + value

        average = total/length
        self.txtAverageResult.setText(str(average))

if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    MainWindow = QtWidgets.QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(MainWindow)
    MainWindow.show()
    sys.exit(app.exec())
