# Form implementation generated from reading ui file 'financialplanner2.ui'
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
        self.btnCalculate = QtWidgets.QPushButton(parent=self.centralwidget)
        self.btnCalculate.setGeometry(QtCore.QRect(350, 240, 75, 23))
        self.btnCalculate.setObjectName("btnCalculate")
        self.lstPortfolio = QtWidgets.QListWidget(parent=self.centralwidget)
        self.lstPortfolio.setGeometry(QtCore.QRect(565, 121, 181, 321))
        self.lstPortfolio.setObjectName("lstPortfolio")
        self.lblAnnual = QtWidgets.QLabel(parent=self.centralwidget)
        self.lblAnnual.setGeometry(QtCore.QRect(170, 90, 151, 16))
        self.lblAnnual.setObjectName("lblAnnual")
        self.txtAnnual = QtWidgets.QLineEdit(parent=self.centralwidget)
        self.txtAnnual.setGeometry(QtCore.QRect(330, 90, 121, 20))
        self.txtAnnual.setObjectName("txtAnnual")
        self.lblTime = QtWidgets.QLabel(parent=self.centralwidget)
        self.lblTime.setGeometry(QtCore.QRect(170, 140, 151, 16))
        self.lblTime.setObjectName("lblTime")
        self.txtTime = QtWidgets.QLineEdit(parent=self.centralwidget)
        self.txtTime.setGeometry(QtCore.QRect(330, 140, 121, 20))
        self.txtTime.setObjectName("txtTime")
        self.chkPlan = QtWidgets.QCheckBox(parent=self.centralwidget)
        self.chkPlan.setGeometry(QtCore.QRect(350, 190, 67, 18))
        self.chkPlan.setObjectName("chkPlan")
        self.lblPortfolio = QtWidgets.QLabel(parent=self.centralwidget)
        self.lblPortfolio.setGeometry(QtCore.QRect(570, 90, 151, 16))
        self.lblPortfolio.setObjectName("lblPortfolio")
        self.btnClear = QtWidgets.QPushButton(parent=self.centralwidget)
        self.btnClear.setGeometry(QtCore.QRect(210, 370, 75, 23))
        self.btnClear.setObjectName("btnClear")
        self.btnExit = QtWidgets.QPushButton(parent=self.centralwidget)
        self.btnExit.setGeometry(QtCore.QRect(450, 370, 75, 23))
        self.btnExit.setObjectName("btnExit")
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

        # connects the calculate button to the calculate function
        self.btnCalculate.clicked.connect(self.calculate)

        # connects the clear button to the clear function
        self.btnClear.clicked.connect(self.clear)

        # connects the exit button to the exit function
        self.btnExit.clicked.connect(self.exit)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "MainWindow"))
        self.btnCalculate.setText(_translate("MainWindow", "Calculate"))
        self.lblAnnual.setText(_translate("MainWindow", "Enter Annual Amount"))
        self.lblTime.setText(_translate("MainWindow", "Enter Total Time"))
        self.chkPlan.setText(_translate("MainWindow", "Plan 2040"))
        self.lblPortfolio.setText(_translate("MainWindow", "Portfolio Value"))
        self.btnClear.setText(_translate("MainWindow", "Clear"))
        self.btnExit.setText(_translate("MainWindow", "Exit"))


    def calculate(self):
        annual_amount = self.txtAnnual.text()
        total_time = self.txtTime.text()
        current_porftolio_value = 0
        years = 0

        if(annual_amount.isdigit() and total_time.isdigit()):
            annual_amount = int(annual_amount)
            total_time = int(total_time)

            if(self.chkPlan.isChecked):
                rate = 7.0
            else:
                rate = 5.0

            for each_year in range(0, total_time):
                current_porftolio_value = (current_porftolio_value + annual_amount) * (1 + rate/100)
                years = years + 1
                currency = f"${current_porftolio_value:,.2f}"
                self.lstPortfolio.addItem(str(years) + "    " + currency)
        else:
            msg = QMessageBox()
            msg.setWindowTitle("Financial Planner")
            msg.setText("Please enter valid inputs for annual amount and time taken")
            msg.exec()

    def clear(self):

        self.txtAnnual.clear()
        self.txtTime.clear()
        self.chkPlan.setChecked(False)
        self.lstPortfolio.clear()
        
    def exit(self):
        sys.exit()

if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    MainWindow = QtWidgets.QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(MainWindow)
    MainWindow.show()
    sys.exit(app.exec())
