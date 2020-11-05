import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.tree import DecisionTreeClassifier
from sklearn import metrics
from sklearn.metrics import confusion_matrix
from matplotlib.colors import ListedColormap

def main():
    data = pd.read_csv('/Users/jyli/myGit/java4lucy/data/input.txt')
    print(data.head())
   # decisionTree(data)

##  test female survival rate
    dFemale = data.copy()
    dFemale = dFemale[dFemale['gender'] == 0]
    print (dFemale)
    surv = dFemale['Survived/Not']
    print(type(surv))
    asn = surv.to_numpy()
    print(asn.sum()/asn.size)


    #print(data['gender']==0 & data['Age'] < 9)
    print(data['Age'] < 9)
    print(data['gender']==0 )
    d2 = data.copy()



    d2 = d2[d2['gender']==0]
    #d2 = d2[d2['gender']==1]
    print(d2.head())

    d2 = d2[d2['Age']<9]
    print(d2.head())

    d2 = d2[d2['Sibling']<2.9]
    print(d2.head())

   # print(d2['Survived/Not'])
    surv = d2['Survived/Not']
    print(type(surv))

    asn = surv.to_numpy()

    print(asn.sum()/asn.size)


    #data <- data[data['gender']==0]
   # data[~data['gender']==0 & data['Age'] < 9]
   # print(data['gender'].head())


def decisionTree ():
    dt = pd.read_csv('/Users/jyli/myGit/java4lucy/data/input.txt')
    feature_cols = ['gender',  'Age' , 'SiblingAge']
    X = dt.iloc[:,[0,1,2]].values
    y = dt.iloc[:,3].values

    #from sklearn.model_selection import train_test_split
    X_train, X_test, y_train, y_test =  train_test_split(X,y,test_size = 0.25, random_state= 0)

    print(X_test[0:5,])
    #feature scaling

    sc_X = StandardScaler()
    X_train = sc_X.fit_transform(X_train)
    X_test = sc_X.transform(X_test)

    #from sklearn.tree import DecisionTreeClassifier

    classifier = DecisionTreeClassifier()
    classifier = classifier.fit(X_train,y_train)

    #prediction
    #Accuracy

    y_pred = classifier.predict(X_test)


    #from sklearn import metrics

    print('Accuracy Score:', metrics.accuracy_score(y_test,y_pred))


    ##  test on the know data
    dt2 = pd.read_csv('/Users/jyli/myGit/java4lucy/data/output.txt')
    feature_cols = ['gender',  'Age' , 'SiblingAge']
    X2 = dt2.iloc[:,[0,1,2]].values
    y2 = dt2.iloc[:,3].values
    print(X2[0:5,])
    X2 = sc_X.fit_transform(X2)
    y2_pred = classifier.predict(X2)

    print('Test Accuracy Score:', metrics.accuracy_score(y2,y2_pred))
   # print (y2_pred.size)
   # print(y2.size)
    #print(X)
if __name__ == '__main__':
 #   main()
    decisionTree ()
