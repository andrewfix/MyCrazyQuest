package entity;

import engine.QuestStateNode;

public class TakeElevatorUpState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
int x=1;
    }

    @Override
    public void afterShow(Player person) {
        QuestStateNode forward;
        if (person.getItems().contains(PlayerItemType.ANTIVIRUS)) {
            forward = person.getQuestContext().findAllStateNodeByName("finishWin");
        } else {
            forward = person.getQuestContext().findAllStateNodeByName("finishLoss");
        }

        forward.setShowNode(true);
        person.getQuestContext().getCurrentStateNode().setForward(forward);
    }
}
