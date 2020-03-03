package ru.ok.adminapp.core.connectionWithJournal;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMICommentInterface extends Remote {

    List<RMIComment> getComments(List<Pair> postIds) throws RemoteException;

}