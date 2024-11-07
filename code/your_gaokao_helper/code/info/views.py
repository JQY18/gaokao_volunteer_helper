from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import UserProfile
from .utils import result1, result2


@api_view(['POST'])
def get_data(request):
    email = request.data.get('email')
    score = request.data.get('score')
    rank = request.data.get('rank')
    subject = request.data.get('subject')
    interest = request.data.get('interest')

    # 尝试从数据库中获取 UserProfile 对象
    try:
        user = UserProfile.objects.get(email=email)
        # 如果找到了，就更新它
        user.score = score
        user.rank = rank
        user.subject = subject
        user.interest = interest
    except UserProfile.DoesNotExist:
        # 如果没有找到，就创建一个新的 UserProfile 对象
        user = UserProfile(
            email=email,
            score=score,
            rank=rank,
            subject=subject,
            interest=interest
        )

    answer1 = result1(rank, subject, interest)
    answer2 = result2(rank, subject, interest)
    user.finalinfo = answer2
    user.save()

    return Response({'answer1': answer1, 'answer2': answer2}, status=status.HTTP_200_OK)
