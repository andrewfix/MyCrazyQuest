package engine;

import entity.DefaultQuestState;
import entity.Player;
import entity.QuestState;
import exception.NewQuestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.QuestScenarioLoaderService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestContextTest {

    private QuestScenarioLoaderService scenarioLoaderService;
    URL fileName = this.getClass().getResource("/scenario.yaml");

    private QuestContext<QuestState<Player>, Player> context;
    private Player person;

    @BeforeEach
    void setUp() throws NewQuestException, IOException {
        scenarioLoaderService = new QuestScenarioLoaderService();
        scenarioLoaderService.loadFromYaml(fileName);
        var scenario = scenarioLoaderService.getInitialState();
        context = new QuestContext<>(scenario);
        context.setDefaultState(new DefaultQuestState());
        person = new Player(context);
        context.setEntity(person);
    }

    @Test
    void testConstructor_InitStateNodeNotNull() {
        assertNotNull(context.getCurrentStateNode(), "InitStateNode is null");
    }

    @Test
    void testSetCurrentStateNode_ByName() {
        context.setCurrentStateNode("officeRoom");
        assertNotNull(context.getCurrentStateNode().getName());
        assertEquals("officeRoom", context.getCurrentStateNode().getName());
    }

    @Test
    void testSetCurrentStateNode_ByNode() {
        var node = context.findStateNodeByName("openSafe");
        context.setCurrentStateNode(node);
        assertNotNull(context.getCurrentStateNode().getName());
        assertEquals("openSafe", context.getCurrentStateNode().getName());
    }

    @Test
    void createStateInstance_DefaultStateInstanceNotNull() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var instance = context.createStateInstance();
        assertNotNull(instance, "Default class is null");
    }

    @Test
    void createStateInstance_ValidClassName() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        context.setCurrentStateNode("officeRoom");
        var instance = context.createStateInstance();
        assertNotNull(instance, "Экземпляр состояния не должен быть null для корректного имени класса.");
        assertTrue(instance instanceof entity.OfficeRoomState);
    }

    @Test
    void findAllStateNodeByName_Exception() {
        QuestStateNode result = context.findAllStateNodeByName("NonExistingNode");
        assertNull(result, "Результат должен быть null при выбрасывании исключения.");
        result = context.findStateNodeByName("NonExistingNode");
        assertNull(result, "Результат должен быть null при выбрасывании исключения.");
    }

    @Test
    void testIsQuestEnded() {
        // Изначально, квест не должен быть завершен
        assertFalse(context.isQuestEnded(), "Квест не должен быть завершен при наличии переходов.");
        // Переключаемся на узел без переходов
        context.setCurrentStateNode("finishDeath");
        // Теперь квест должен быть завершен
        assertTrue(context.isQuestEnded(), "Квест должен быть завершен, если нет доступных переходов.");
    }

    @Test
    void testSetCurrentStateNode_Throws() {
        assertThrows(Exception.class, ()->{context.setCurrentStateNode((QuestStateNode) null);});
        assertThrows(Exception.class, ()->{context.setCurrentStateNode((String) null);});
        assertThrows(Exception.class, ()->{context.setCurrentStateNode("NonExistenNode");});
    }

    @Test
    void testGetEntity() {
        var entity = context.getEntity();
        assertEquals(person.getClass(), entity.getClass());
    }
}