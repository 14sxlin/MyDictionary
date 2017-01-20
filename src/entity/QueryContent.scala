package entity

/**
  * Created by LinSixin on 2016/12/26.
  */

/**
  * ��ѯ����
  * @param content  Ҫ��ѯ������
  * @param result δ��ʽ���Ĳ�ѯ���
  */
sealed abstract class QueryContent(val content:String,var result:String="")

/**
  * ��ѯӢ�����
  * @param sentence
  */
case class QESentence(sentence:String) extends QueryContent(sentence)

/**
  * ��ѯӢ�ﵥ��
  * @param word
  */
case class QEWord(word:String) extends QueryContent(word)

/**
  * ��ѯ���ĵ���
  * @param word
  */
case class QCWord(word:String) extends QueryContent(word)

/**
  * ��ѯ���ľ���
  * @param sentence
  */
case class QCSentence(sentence: String) extends QueryContent(sentence)